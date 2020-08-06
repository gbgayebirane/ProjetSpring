package com.gb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gb.dao.IFormation;
import com.gb.dao.ILieu;
import com.gb.dao.IUser;
import com.gb.entities.Formation;
import com.gb.entities.Lieu;
import com.gb.entities.User;

@Controller
public class FormationController {

	@Autowired
	private IFormation iform;
	@Autowired
	private IUser userdao;
	
	 @RequestMapping(value="/Formation/liste")
	public String liste(ModelMap modelmap)
	{
		 List<User> users =userdao.findAll();
		List<Formation> form=iform.findAll();
		modelmap.put("liste_form",  form);
		 modelmap.put("liste_users",users);
		 modelmap.put("formation",new Formation());
		return "formation/liste";
	}
	 @RequestMapping(value="/Formation/add")
	 public String add(int id, String nom, String datedebut, int duree, int idUser)
		{
			
			Formation f= new Formation();
			f.setId(id);
			f.setNom(nom);
			try {
				f.setDatedebut(new SimpleDateFormat("yyyy-MM-dd").parse((datedebut)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//f.setDatedebut(datedebut);
			f.setDuree(duree);
			User u=new User();
			u=userdao.getOne(idUser);
			f.setUser(u);
			try 
			{
				iform.save(f);
				iform.flush();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			 return  "redirect:/Formation/liste";
		}
	 @RequestMapping(value="/Formation/delete",method=RequestMethod.GET)
		public String delete(int id)
		{
			try
			{
				iform.delete(iform.getOne(id));
				iform.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return  "redirect:/Formation/liste";
		}	
		@RequestMapping(value="/Formation/edit" , method=RequestMethod.GET)
		public String edit(int id ,ModelMap  model)
		{
			List<User> users =userdao.findAll();
			List<Formation> form=iform.findAll();
			model.put("liste_form",  form);
			 model.put("liste_users",users);
			 model.put("formation",iform.getOne(id));
			 
			return "formation/liste";
			
		}
}

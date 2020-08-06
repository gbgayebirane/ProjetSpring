package com.gb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gb.dao.ILieu;
import com.gb.dao.IUser;
import com.gb.entities.Lieu;
import com.gb.entities.User;


@Controller
public class LieuController
{


	@Autowired
	private ILieu lieudao;
    @Autowired
    private IUser userdao;
	
	@RequestMapping(value="/Lieu/liste")
	 public String liste(ModelMap model)
    {
		List<Lieu> lieux =lieudao.findAll();
		List<User> users=userdao.findAll();
        model.put("liste_lieux",lieux);
        model.put("liste_users",users);
        model.put("lieu",new Lieu());
        return "lieu/liste";
    }
	@RequestMapping(value="/Lieu/add",method=RequestMethod.POST)
	//public String add(int id,String nom,int idUser)
	public String add(int id,String nom,int idUser)
	{
		
		Lieu l=new Lieu();
		l.setId(id);
		l.setNom(nom);
		User u=new User();
		u=userdao.getOne(idUser);
		l.setUser(u);
		try 
		{
			lieudao.save(l);
			lieudao.flush();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 return  "redirect:/Lieu/liste";
	}
	@RequestMapping(value="/Lieu/delete",method=RequestMethod.GET)
	public String delete(int id)
	{
		try
		{
			lieudao.delete(lieudao.getOne(id));
			lieudao.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  "redirect:/Lieu/liste";
	}	
	@RequestMapping(value="/Lieu/edit" , method=RequestMethod.GET)
	public String edit(int id ,ModelMap  model)
	{
			List<Lieu> lieux =lieudao.findAll();
			List<User> users=userdao.findAll();
	        model.put("liste_lieux",lieux);
	         model.put("liste_users",users);
	        Lieu lieu=lieudao.getOne(id);
	        model.put("lieu",lieu);
	        return "lieu/liste";
		
	}
		
		
		
		
		
		
		
	}


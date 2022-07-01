package com.spring.board.board.controller;

import com.spring.board.board.vo.ArticleVO;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardController {
	
	public String listArticles(@RequestParam("page") int page,int listSize, Model model) throws Exception;

//	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;

	public String addNewArticle (@ModelAttribute("article") ArticleVO articleVO) throws Exception;

	public String viewArticle(@RequestParam("articleNO") int articleNO,
			                        Model model) throws Exception;
	public String removeArticle(@RequestParam("articleNO") int articleNO,
									  HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String modArticle(@ModelAttribute("article") ArticleVO articleVO,Model model) throws Exception;
	//public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest,  HttpServletResponse response) throws Exception;
//	public ResponseEntity  removeArticle(@RequestParam("articleNO") int articleNO,
//                              HttpServletRequest request, HttpServletResponse response) throws Exception;

}

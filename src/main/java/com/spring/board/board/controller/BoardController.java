package com.spring.board.board.controller;

import com.spring.board.board.dto.ArticleDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

public interface BoardController {
	
	public String listArticles(@RequestParam("page") int page,
							   @RequestParam("listSize") int listSize,
							   @RequestParam("type") String type,
							   @RequestParam("keyword") String keyword, Model model) throws Exception;

//	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;

	public String addNewArticle (@ModelAttribute("article") ArticleDTO ArticleDTO) throws Exception;

	public String viewArticle(@RequestParam("articleNO") int articleNO,
			                        Model model) throws Exception;
	public String removeArticle(@RequestParam("articleNO") int articleNO,
								@RequestParam("articleURL") String articleURL) throws Exception;
	public String modArticle(@ModelAttribute("article") ArticleDTO ArticleDTO, Model model) throws Exception;
	//public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest,  HttpServletResponse response) throws Exception;
//	public ResponseEntity  removeArticle(@RequestParam("articleNO") int articleNO,
//                              HttpServletRequest request, HttpServletResponse response) throws Exception;


}

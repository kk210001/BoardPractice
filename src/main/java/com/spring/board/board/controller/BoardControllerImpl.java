package com.spring.board.board.controller;

import com.spring.board.board.service.BoardService;
import com.spring.board.board.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller("boardController")
public class BoardControllerImpl  implements BoardController{
	private final BoardService boardService;

	@Autowired
	public BoardControllerImpl(BoardService boardService) {
		this.boardService = boardService;
	}

	@Override
	@RequestMapping(value= "/board/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("리스트 호출");
//		ModelAndView mav = new ModelAndView();
//		List<ArticleVO> articlesList = boardService.listArticles();
//		mav.addObject("articlesList", articlesList);
//		mav.setViewName("listArticles");
		return getBoardList();
	}

	@Override
	@RequestMapping(value="/board/addNewArticle.do" ,method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addNewArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("id", request.getParameter("id"));
		articleMap.put("title", request.getParameter("title"));
		articleMap.put("content", request.getParameter("content"));
		boardService.addNewArticle(articleMap);
		//		ModelAndView mav = new ModelAndView();
//		List<ArticleVO> articlesList = boardService.listArticles();
//		mav.addObject("articlesList", articlesList);
//		mav.setViewName("listArticles");
		return getBoardList();
	}

	@Override
	@RequestMapping(value="/board/removeArticle.do" ,method = RequestMethod.POST)
	public ModelAndView removeArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		boardService.removeArticle(articleNO);
//		ModelAndView mav = new ModelAndView();
//		List<ArticleVO> articlesList = boardService.listArticles();
//		mav.addObject("articlesList", articlesList);
//		mav.setViewName("listArticles");
		return getBoardList();
	}

	@Override
	@RequestMapping(value="/board/viewArticle.do" ,method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO,
									HttpServletRequest request, HttpServletResponse response) throws Exception{
//		Map articleMap = boardService.viewArticle(articleNO);
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("viewArticle");
//		mav.addObject("articleMap", articleMap);
		return viewBoard(articleNO);
	}

	@Override
	@RequestMapping(value="/board/modArticle.do" ,method = RequestMethod.POST)
	public ModelAndView modArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String articleNo = request.getParameter("articleNO");

		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("title", request.getParameter("title"));
		articleMap.put("content", request.getParameter("content"));
		articleMap.put("articleNO", articleNo);

		boardService.modArticle(articleMap);

		int boardNO= Integer.parseInt(articleNo);
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("viewArticle");
//		Map viewBorad=boardService.viewArticle(boardNO);
//		mav.addObject("articleMap", viewBorad);
		return viewBoard(boardNO);
	}


	@RequestMapping(value = "/board/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("articleForm");
		return mav;
	}

	private ModelAndView getBoardList() throws Exception {
		ModelAndView mav = new ModelAndView();
		List<ArticleVO> articlesList = boardService.listArticles();
		mav.addObject("articlesList", articlesList);
		mav.setViewName("listArticles");
		return mav;
	}
	private ModelAndView viewBoard(int articleNO) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewArticle");
		Map articleMap=boardService.viewArticle(articleNO);
		mav.addObject("articleMap", articleMap);
		return mav;
	}

}

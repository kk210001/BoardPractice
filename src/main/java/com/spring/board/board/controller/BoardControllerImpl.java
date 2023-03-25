package com.spring.board.board.controller;

import com.spring.board.board.dto.ArticleDTO;
import com.spring.board.board.service.BoardService;
import com.spring.board.paging.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Slf4j
@Controller("boardController")
@RequestMapping("/board")
public class BoardControllerImpl  implements BoardController{

	private final BoardService boardService;

	@Autowired
	public BoardControllerImpl(BoardService boardService) {
		this.boardService = boardService;
	}


	@Override
	@RequestMapping(value = "/listArticles.do")
	public String listArticles(@RequestParam(required = false,defaultValue = "1") int page,
									 @RequestParam(required = false,defaultValue = "10") int listSize,
							  		 @RequestParam(required = false) String type,
							  		 @RequestParam(required = false) String keyword,
									 Model model) throws Exception {

		log.info("게시글 출력 페이지 : {}", page);

		Pagination pagination = boardService.paging(page, listSize, type, keyword);
		model.addAttribute("pagination", pagination);

		List<ArticleDTO> articlesList = boardService.listArticles(pagination);
		model.addAttribute("articlesList", articlesList);

		return "board/listArticles";
	}


	@Override
	@PostMapping("/addNewArticle.do")
	public String addNewArticle(@ModelAttribute("article") ArticleDTO ArticleDTO) throws Exception {

		Map<String, String> articleMap = setArticleMap(ArticleDTO);
		boardService.addNewArticle(articleMap);

		return "redirect:/board/listArticles.do";
	}

	//add Article
	private Map<String, String> setArticleMap(ArticleDTO ArticleDTO) {
		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("id", ArticleDTO.getId());
		articleMap.put("title", ArticleDTO.getTitle());
		articleMap.put("content", ArticleDTO.getContent());
		return articleMap;
	}

	@Override
	@PostMapping("/removeArticle.do")
	public String removeArticle(@RequestParam("articleNO") int articleNO,
			@RequestParam("articleURL") String articleURL) throws Exception {

		boardService.removeArticle(articleNO);
		log.info("delete articleNO = {}", articleNO);
		return "redirect:" + articleURL;
	}

	@Override
	@RequestMapping(value = "/viewArticle.do",method = {RequestMethod.GET,RequestMethod.POST})
	public String viewArticle(@RequestParam("articleNO") int articleNO,
									Model model) throws Exception{
		return viewBoard(articleNO, model);
	}

	@Override
	@PostMapping("/modArticle.do")
	public String modArticle(@ModelAttribute("article") ArticleDTO ArticleDTO, Model model) throws Exception {

		Map<String, String> articleMap = getArticleMap(ArticleDTO);
		boardService.modArticle(articleMap);

		int articleNo = Integer.parseInt(articleMap.get("articleNO"));

		return viewBoard(articleNo, model);
	}

	//mod Article
	private Map<String, String> getArticleMap(ArticleDTO ArticleDTO) {
		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("title", ArticleDTO.getTitle());
		articleMap.put("content", ArticleDTO.getContent());
		articleMap.put("articleNO", ArticleDTO.getArticleNO() + "");
		return articleMap;
	}


	@GetMapping("/*Form.do") //글쓰기 클릭시
	private String form(Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		Object loginMember = session.getAttribute("loginMember");
		log.info("addArticle who = {}",loginMember);
		model.addAttribute("member", loginMember);
		return "board/articleForm";
	}

	private String viewBoard(int articleNO, Model model) throws Exception {

		Map articleMap=boardService.viewArticle(articleNO);
		model.addAttribute("articleMap", articleMap);
		return "board/viewArticle";
	}




}

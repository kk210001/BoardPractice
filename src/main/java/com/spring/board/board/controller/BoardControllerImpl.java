package com.spring.board.board.controller;

import com.spring.board.board.service.BoardService;
import com.spring.board.board.service.BoardServiceImpl;
import com.spring.board.board.vo.ArticleVO;
import com.spring.board.paging.PageMaker;
import com.spring.board.paging.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

		List<ArticleVO> articlesList = boardService.listArticles(pagination);
		model.addAttribute("articlesList", articlesList);

		return "listArticles";
	}


	@Override
	@PostMapping("/addNewArticle.do")
	public String addNewArticle(@ModelAttribute("article") ArticleVO articleVO) throws Exception {

		Map<String, String> articleMap = setArticleMap(articleVO);
		boardService.addNewArticle(articleMap);

		return "redirect:/board/listArticles.do";
	}

	//add Article
	private Map<String, String> setArticleMap(ArticleVO articleVO) {
		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("id", articleVO.getId());
		articleMap.put("title", articleVO.getTitle());
		articleMap.put("content", articleVO.getContent());
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
	public String modArticle(@ModelAttribute("article") ArticleVO articleVO,Model model) throws Exception {

		Map<String, String> articleMap = getArticleMap(articleVO);
		boardService.modArticle(articleMap);

		int articleNo = Integer.parseInt(articleMap.get("articleNO"));

		return viewBoard(articleNo, model);
	}

	//mod Article
	private Map<String, String> getArticleMap(ArticleVO articleVO) {
		Map<String, String> articleMap=new HashMap<>();
		articleMap.put("title", articleVO.getTitle());
		articleMap.put("content", articleVO.getContent());
		articleMap.put("articleNO", articleVO.getArticleNO() + "");
		return articleMap;
	}


//	@GetMapping("/*Form.do") //글쓰기 클릭시
//	private String form() throws Exception {
//		return "articleForm";
//	}

	private String viewBoard(int articleNO, Model model) throws Exception {

		Map articleMap=boardService.viewArticle(articleNO);
		model.addAttribute("articleMap", articleMap);
		return "viewArticle";
	}




}

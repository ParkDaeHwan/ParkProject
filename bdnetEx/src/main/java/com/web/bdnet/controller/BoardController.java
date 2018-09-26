package com.web.bdnet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.bdnet.dao.BoardDAO;
import com.web.bdnet.vo.BoardVO;
import com.web.bdnet.vo.FileVO;

// 게시글을 처리하는 Controller
@Controller
public class BoardController {
	@Autowired
	private BoardDAO bDAO = null;
	
	// 게시글 목록 - GET
	@RequestMapping(value="/board.do", method=RequestMethod.GET)
	public String board(Model model, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="type", defaultValue="brd_title") String type, @RequestParam(value="text", defaultValue="") String text) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("text", text);
			int totPage = bDAO.selectBoardTotPage(map);
			model.addAttribute("totPage", (totPage-1)/10 + 1);
			
			map.put("page", (page - 1) * 10);
			List<BoardVO> list = bDAO.selectBoardList(map);
			model.addAttribute("list", list);
			
			return "v1_board";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect: error.do";
		}		
	}
	
	// 게시글 쓰기 - GET
	@RequestMapping(value="/boardw.do", method=RequestMethod.GET)
	public String boardw(Model model) {
		try {
			BoardVO vo = new BoardVO();
			vo.setBrd_no(bDAO.selectBoardMaxNo()+1);
			model.addAttribute("vo", vo);
			
			return "v1_boardw";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect: error.do";
		}
	}
	
	// 게시글 쓰기 - POST
	@RequestMapping(value="/boardw.do", method=RequestMethod.POST)
	public String boardw(@ModelAttribute("vo") BoardVO vo, MultipartHttpServletRequest request) {
		try {
			// 게시글 DB에 저장
			int ret = bDAO.insertBoardOne(vo);
			if(ret == 0) {
				return "redirect: error.do";
			}
			
			// 파일정보를 DB에 저장
			Map<String, MultipartFile> map = request.getFileMap();
			FileVO fVO = new FileVO();
			for(int i = 0; i < map.size(); i++) {
				MultipartFile tfile = map.get("file"+(i+1));
				// 파일 첨부 유무를 확인한다.
				if(tfile != null && !tfile.getOriginalFilename().equals("")) {
					fVO.setBrd_no(vo.getBrd_no());
					if(i == 0) {
						fVO.setFi_name1(tfile.getOriginalFilename());
						fVO.setFi_content1(tfile.getBytes());
					}
					if(i == 1) {
						fVO.setFi_name2(tfile.getOriginalFilename());
						fVO.setFi_content2(tfile.getBytes());
					}
					if(i == 2) {
						fVO.setFi_name3(tfile.getOriginalFilename());
						fVO.setFi_content3(tfile.getBytes());
					}
				}
			}
			if(fVO.getBrd_no() != 0) {
				int ret2 = bDAO.insertBoardFile(fVO);
				if(ret2 == 0) {
					return "redirect: error.do";
				}
			}			
			
			return "redirect: board.do";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect: error.do";
		}
	}
	
	// 게시글 내용(조회수 처리) - POST
	@RequestMapping(value="/boardc.do", method=RequestMethod.POST)
	public String boardc(@RequestParam(value="no", defaultValue="0") int no, @RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="type", defaultValue="") String type, @RequestParam(value="text", defaultValue="") String text) {
		try {
			int ret = bDAO.updateBoardHit(no);
			if(ret > 0) {
				return "redirect:boardc.do?no="+no+"&page="+page+"&type="+type+"&text="+text;
			}
			return "redirect: board.do?page="+page+"&type="+type+"&text="+text;
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect: error.do";
		}
	}
	
	// 게시글 내용 - GET
	@RequestMapping(value="/boardc.do", method=RequestMethod.GET)
	public String boardc(Model model, @RequestParam(value="no", defaultValue="0") int no, @RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="type", defaultValue="") String type, @RequestParam(value="text", defaultValue="") String text) {
		try {
			if(no <= 0) {
				return "redirect: board.do?page="+page+"&type="+type+"&text="+text;
			}
			model.addAttribute("vo", bDAO.selectBoardOne(no));
			
			model.addAttribute("prev", bDAO.selectBoardPrev(no));
			model.addAttribute("next", bDAO.selectBoardNext(no));
			
			return "v1_boardc";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect: error.do";
		}
	}
	
	// 게시글 수정 - GET
	@RequestMapping(value="/boardm.do", method=RequestMethod.GET)
	public String boardm(Model model, @RequestParam(value="no", defaultValue="0") int no) {
		try {
			model.addAttribute("vo", bDAO.selectBoardOne(no));
			
			return "v1_boardm";
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect: error.do";
		}
	}
	
	// 게시글 수정 - POST
	@RequestMapping(value="/boardm.do", method=RequestMethod.POST)
	public String boardm(@ModelAttribute("vo") BoardVO vo) {
		try {
			int ret = bDAO.updateBoardOne(vo);
			if(ret == 0) {
				return "redirect: error.do";
			}
			
			return "redirect: boardc.do?no="+vo.getBrd_no();
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect: error.do";
		}
	}
	
	// 게시글의 파일처리 - GET
	@RequestMapping(value="/selectFile.do", method=RequestMethod.GET)
	public ResponseEntity<byte[]> selectFile(@RequestParam("no") int no, @RequestParam("idx") int idx, HttpServletRequest request){
		try {
			FileVO vo = bDAO.selectBoardFile(no);
			byte[] fileData = null;
			String fileName = null;
			if(idx == 1) {
				fileName = vo.getFi_name1();
				fileData = vo.getFi_content1();
			}
			if(idx == 2) {
				fileName = vo.getFi_name2();
				fileData = vo.getFi_content2();
			}
			if(idx == 3) {
				fileName = vo.getFi_name3();
				fileData = vo.getFi_content3();
			}
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename='"+new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"'"); // 파일 다운로드

			return new ResponseEntity<byte[]>(fileData, headers, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

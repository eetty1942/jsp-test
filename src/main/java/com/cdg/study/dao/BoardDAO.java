package com.cdg.study.dao;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.cdg.study.entity.BoardDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 create table board
 ( num number(4) primary key,
 author varchar2(20) not null,
 title varchar2(20) not null,
 content varchar2(4000),
 writeday date default sysdate,
 readcnt number(4) default 0 );

 create sequence board_seq;

 */

/**
 * 게시판 데이터처리를 위한 DAO
 * JSON처리를 위한 ObjectMapper의 객체생성 리소스를 줄이기위해 싱글턴 적용
 * 
 * @author Kanghoon Choi
 */
public class BoardDAO {
	// 각자 환경에 맞게 수정해주세요. /본인_워크스페이스_경로/jsp-test/data.json
	//private final static String DATA_FILE_PATH = "/Users/nhnent/Documents/workspace/jsp-test/data.json";
	private final static String DATA_FILE_PATH = "D:/summer_study/jsp-test/data.json";
	private volatile static BoardDAO instance;
	private static ObjectMapper objectMapper;
	
	private BoardDAO() {
		objectMapper = new ObjectMapper();
	}
	
	public static BoardDAO getInstance() {
		if (instance == null) {
			synchronized (BoardDAO.class) {
				if (instance == null) {
					instance = new BoardDAO();
				}
			}
		}
		return instance;
	}

	/**
	 * 글 목록 보기
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BoardDTO> getList() {
		List<BoardDTO> boardList;
		
		try {
			boardList =  objectMapper.readValue(new File(DATA_FILE_PATH), new TypeReference<List<BoardDTO>>(){});
		} catch (Exception e) {
			boardList = new ArrayList<>();
		}
		
		return boardList;
	}

	/**
	 * 글쓰기
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int write(BoardDTO dto) throws Exception {
		List<BoardDTO> boardDTOList = getList();
		dto.setNum(boardDTOList.size() + 1);
		dto.setWriteday(new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date()));
		boardDTOList.add(dto);
		
		String jsonString = objectMapper.writeValueAsString(boardDTOList);

		FileUtils.writeStringToFile(new File(DATA_FILE_PATH), jsonString);
			
		return 1;
	}

	/**
	 * 글 자세히 보기 
	 * 
	 * 조회수 증가 수정 필요 -> 조회수 카운트는 작동하나 글이 추가가 되어버림
	 * 
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public BoardDTO retrieve(String num) throws Exception {

		int boardNum = Integer.parseInt(num);
		
		List<BoardDTO> boardDTOList = getList();
		BoardDTO result = boardDTOList.stream().filter(x -> x.getNum() == boardNum).findFirst().get();
		
		// 조회수 카운트
		int readcnt = result.getReadcnt(); 
		result.setReadcnt(readcnt+1);	
		
		String jsonString = objectMapper.writeValueAsString(boardDTOList);

		FileUtils.writeStringToFile(new File(DATA_FILE_PATH), jsonString);
		
		return result;
	}

	/**
	 * 글 수정하기
	 * 
	 * 조회수 초기화 문제 해결 필요
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int update(BoardDTO dto) throws Exception {
		// TODO : 해당 메소드를 구현해주세요.
		List<BoardDTO> boardDTOList = getList();
		dto.setWriteday(new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date()));
		
		BoardDTO result = boardDTOList.stream().filter(x -> x.getNum() == dto.getNum()).findFirst().get();
		
		boardDTOList.set(0, dto);

		String jsonString = objectMapper.writeValueAsString(boardDTOList);

		FileUtils.writeStringToFile(new File(DATA_FILE_PATH), jsonString);
		
			
		return 1;
	}

	/**
	 * 글 삭제하기
	 * 
	 * 보완 필요 - 인덱싱 이슈
	 * 
	 * @param num
	 * @return
	 * @throws Exception
	 */
	
	public int delete(String num) throws Exception {
		// TODO : 해당 메소드를 구현해주세요.
		int boardNum = Integer.parseInt(num);
		
		//삭제할 글 데이터 불러오기
		List<BoardDTO> boardDTOList = getList();
		BoardDTO result = boardDTOList.stream().filter(x -> x.getNum() == boardNum).findFirst().get();
		
		boardDTOList.remove(result);

		//해당 글 삭제 후 파일에 그 내용 덮어씌우기
		String jsonString = objectMapper.writeValueAsString(boardDTOList);

		FileUtils.writeStringToFile(new File(DATA_FILE_PATH), jsonString);

		
		
		return 1;
	}

	/**
	 * 검색하기
	 * 
	 * @param searchName
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public List<BoardDTO> search(String searchName, String searchValue) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchName", searchName);
		map.put("searchValue", searchValue);
		
		// TODO : 해당 메소드를 구현해주세요.
		return null;
	}

}

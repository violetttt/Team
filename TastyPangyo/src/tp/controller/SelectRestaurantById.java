package tp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tp.exception.NotFoundRestaurantIdException;
import tp.service.impl.RestaurantServiceImpl;
import tp.service.impl.ReviewServiceImpl;
import tp.vo.Member;
import tp.vo.Restaurant;

public class SelectRestaurantById extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청파라미터 조회
		int resId = Integer.parseInt(req.getParameter("restaurantId"));
		
		HttpSession session = req.getSession();
		//2. 처리
		
		RestaurantServiceImpl service = RestaurantServiceImpl.getInstance();
		ReviewServiceImpl rs = ReviewServiceImpl.getInstance();
		//Member member = ((Member)req.getSession().getAttribute("login")); 로그인 정보를 받아올 변수선언
		// 레스토랑 아이디로 평균별점 가져오기 , 조회수 올리기
		//
		try{
			Restaurant res = service.selectRestaurantByID(resId);
			int hits = res.getHits()+1;
			res.setHits(hits);
		
			
			service.modRestaurant(res);
			req.setAttribute("selectRes", res);
		}catch(NotFoundRestaurantIdException e){
			req.setAttribute("errorMessage", e.getMessage());
		}
		
		
		//응답
		req.getRequestDispatcher("/restaurant/restaurant_detail_information.jsp").forward(req, resp);
	}
}

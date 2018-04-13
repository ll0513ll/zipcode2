	package kr.co.bit.controller;
	
	import java.io.IOException;
	import java.util.ArrayList;
	
	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import kr.co.bit.dao.MemberDAO;
	import kr.co.bit.dao.ZipcodeDAO;
	import kr.co.bit.vo.MemberVO;
	import kr.co.bit.vo.ZipcodeVO;
	
	
	public class CommandController extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			this.doPost(req, resp);
			//System.out.println("in");
		}
		
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			request.setCharacterEncoding("UTF-8");
			
			String cmd = request.getParameter("cmd");
			cmd= cmd==null?"":cmd;
			
			String url = "./mvc/home.jsp";
			if(cmd.equals("searchDong")) {
				String dong = request.getParameter("dong");
				//dong = new String(dong.getBytes("ISO-8859-1"),"UTF-8");
				ZipcodeDAO dao = new ZipcodeDAO();
				ArrayList<ZipcodeVO> list = dao.getSearchList(dong);
				request.setAttribute("list", list);
				url = "./postal.jsp";
			} else if(cmd.equals("viewRegist")) {
				url = "./mvc/regist_member.jsp";
			} else if(cmd.equals("regist")){
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String zip1 = request.getParameter("zip1");
				String zip2 = request.getParameter("zip2");
				String addr1 = request.getParameter("addr1");
				String addr2 = request.getParameter("addr2");
				String tool = request.getParameter("tool");
				String project = request.getParameter("project");
				String[] langs = request.getParameterValues("lang");
				String[] temp = {"","","",""};
				for(String index : langs){
					int idx = Integer.parseInt(index);
					temp[idx] = index;
				}
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPw(pw);
				vo.setName(name);
				vo.setEmail(email);
				vo.setZipcode(zip1+"-"+zip2);
				vo.setAddr1(addr1);
				vo.setAddr2(addr2);
				vo.setTool(tool);
				vo.setProject(project);
				vo.setLangs(temp);
				//out.print(vo);
				//list.add(vo);
				request.setAttribute("message", "success");
				//response.sendRedirect("storage.jsp");
				url = "./mvc/home.jsp";
			}  else if(cmd.equals("make")){
				
				String path = this.getServletContext().getRealPath("WEB-INF/file/zipcode.csv");
				ZipcodeDAO dao = new ZipcodeDAO();
				boolean flag = dao.insertV2(path);
				request.setAttribute("result", flag?"success":"fail");
				url = "./result.jsp";
			
			} else if(cmd.equals("postal")) {
				
				url = "./postal.jsp";
		
			} else if(cmd.equals("idcheck")) {
				
				MemberVO vo = null;
				MemberDAO dao = new MemberDAO();
				vo = dao.select(request.getParameter("id"));
				url = "./mvc/id_check.jsp";
				
				request.setAttribute("result", vo);
			} else if(cmd.equals("viewIdCheck")) {
			
				url = "./mvc/id_check.jsp?id="+request.getAttribute("id");
			
			} else if(cmd.equals("search")) {
				String id = request.getParameter("id");
				MemberDAO dao = new MemberDAO();
				MemberVO vo = dao.select(id);
				request.setAttribute("vo", vo);
				url = "./mvc/regist_member.jsp";
			
			}else if(cmd.equals("searchAll")) {
				
				MemberDAO dao = new MemberDAO();
				ArrayList list = dao.selectAll();
				request.setAttribute("list",list);
				url = "./mvc/list.jsp";
				
			}else if(cmd.equals("viewIdService")) {
				
				url = "./mvc/id_service.jsp";
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);
			
		}
		
	}

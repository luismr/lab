package wifi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wifi.model.dao.DAO;
import wifi.model.data.Curso;

@Controller
@RequestMapping("/cursos")
public class CursosController {

	@Autowired
	private DAO<Curso> cursoJpaDAO;
	
	@RequestMapping("/list")
	@Transactional(readOnly=true)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("cursos/list");
		mv.addObject("cursos", cursoJpaDAO.listAll());
		
		return mv;
	}
	
	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("cursos/form");
		mv.addObject("action", "add");
		
		return mv;
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = null;
		
		try {
			mv = new ModelAndView("cursos/form");
			mv.addObject("action", "edit");
			
			Curso curso = cursoJpaDAO.read(new Curso(id, ""));
			mv.addObject("curso", curso);
		} catch (Exception e) {
			mv = new ModelAndView("common/message");
			mv.addObject("msg", new Message(MessageType.ERROR, e.getMessage()));
		}
		

		return mv;
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = null;
		
		try {
			mv = new ModelAndView("cursos/form");
			mv.addObject("action", "delete");
			
			Curso curso = cursoJpaDAO.read(new Curso(id, ""));
			mv.addObject("curso", curso);
		} catch (Exception e) {
			mv = new ModelAndView("common/message");
			mv.addObject("msg", new Message(MessageType.ERROR, e.getMessage()));
		}
		
		
		return mv;
	}
	
	@Transactional
	@RequestMapping("/save/{action}")
	public ModelAndView save(Curso curso, @PathVariable String action) {
		Message msg = null;
		
		if ("add".equals(action)) {
			cursoJpaDAO.create(curso);
			msg = new Message(MessageType.INFO, "Curso incluido com sucesso!");
		} else if ("edit".equals(action)) {
			cursoJpaDAO.update(curso);
			msg = new Message(MessageType.INFO, "Curso alterado com sucesso!");
		} else if ("delete".equals(action)) {
			cursoJpaDAO.delete(curso);
			msg = new Message(MessageType.INFO, "Curso excluido com sucesso!");
		} else {
			msg = new Message(MessageType.ERROR, "Operação Inválida!");
		}
		
		ModelAndView mv = new ModelAndView("common/message");
		mv.addObject("msg", msg);
		mv.addObject("back", "/cursos/list");
		
		return mv;
	}
}

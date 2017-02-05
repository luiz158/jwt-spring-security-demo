package org.techytax.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.techytax.domain.Project;
import org.techytax.repository.ProjectRepository;
import org.techytax.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
public class ProjectRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ProjectRepository projectRepository;

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/project", method = RequestMethod.GET)
    public Collection<Project> getProjects(HttpServletRequest request) {
        String username = getUser(request);
        return projectRepository.findByUser(username);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/project", method = { RequestMethod.PUT, RequestMethod.POST })
    public void saveProject(HttpServletRequest request, @RequestBody Project project) {
        String username = getUser(request);
        project.setUser(username);
        projectRepository.save(project);
    }

    @CrossOrigin(origins = "http://localhost:5555")
    @RequestMapping(value = "auth/project/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(HttpServletRequest request, @PathVariable Long id) {
        projectRepository.delete(id);
    }

    private String getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return jwtTokenUtil.getUsernameFromToken(token);
    }

}

package ru.netology.servlet;

import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private PostController controller;
    private static final String POST_PATH = "/api/posts";
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String DELETE_METHOD = "DELETE";

    @Override
    public void init() {
        PostRepository repository = new PostRepository();
        PostService service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // если деплоились в root context, то достаточно этого

        try {
            String path = req.getRequestURI();
            String method = req.getMethod();
            String param = req.getParameter("id");
            // primitive routing
            if (method.equals(GET_METHOD) && path.equals(POST_PATH) && param == null) {
                controller.all(resp);
                return;
            }
            if (method.equals(GET_METHOD) && path.matches(POST_PATH) && !(param == null)) {
                // easy way
                final var id = Long.parseLong(param);
                controller.getById(id, resp);
                return;
            }
            if (method.equals(POST_METHOD) && path.equals(POST_PATH)) {
                controller.save(req.getReader(), resp);
                return;
            }
            if (method.equals(DELETE_METHOD) && path.matches(POST_PATH)) {
                // easy way
                final var id = Long.parseLong(param);
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}


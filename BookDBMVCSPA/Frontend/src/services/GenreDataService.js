import http from "../http-common";

class GenreDataService {
    getAll() {
        return http.get("/genres");
    }

    get(id) {
        return http.get(`/genres/${id}`);
    }

    update(genre) {
        return http.put(`/genres`, genre);
    }

    delete(id) {
        return http.delete(`/genres/${id}`);
    }

    create(genre) {
        return http.post("/genres", genre);
    }
}

export default new GenreDataService();

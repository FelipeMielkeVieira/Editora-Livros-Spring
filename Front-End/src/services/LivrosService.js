import axios from "axios";
const url = "https://localhost:8443/editoralivros/livro";

export class LivrosService {

    postLivro(livro) {
        return axios.post(url, livro);
    }

    getLivros() {
        const config = {
            withCredentials: true,
        };
        return axios.get(url,config)
            .then(response => {
                return(response);
            })
            .catch(error => {
                console.log(error);
            });
    }
    
    getStatus() {
        return axios.get(url + "/status");
    }

    getLivro(isbn) {
        return axios.get(url + "/isbn/" + isbn);
    }

    deleteLivro(isbn) {
        return axios.delete(url + "/" + isbn);
    }

    putLivro(isbn , livro) {
        return axios.put(url + "/" + isbn, livro);
    }

}
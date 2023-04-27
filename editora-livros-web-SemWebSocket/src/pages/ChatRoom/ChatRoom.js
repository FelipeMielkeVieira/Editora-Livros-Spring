import React, { useContext, useEffect, useState } from 'react'
import { Button, Card, TextInput } from "flowbite-react";
import { Paragrafo, Titulo } from "../../components";
import ForwardToInboxIcon from "@mui/icons-material/ForwardToInbox";
import { apiMensagens, WebSocketContext } from '../../services';
import { useParams } from "react-router-dom";
import Cookies from 'js-cookie';

export const ChatRoom = () => {

    const isbn = useParams().isbn
    const [mensagens, setMensagens] = useState([]);
    const [mensagem, setMensagem] = useState({});
    const { enviar, inscrever, stompClient } = useContext(WebSocketContext);

    useEffect(() => {
        async function carregar() {
            await apiMensagens.getMensagensLivro(isbn)
                .then((response) => {
                    setMensagens(response);
                }).catch((error) => {
                    console.log(error);
                })
            setDefaultMensagem();
        }
        carregar();
    }, []);

    useEffect(() => {
        const acaoNovaMensagem = (response) => {
            const mensagemRecebida = JSON.parse(response.body);
            console.log("Mensagem recebida: " + mensagemRecebida);
            setMensagens([...mensagens, mensagemRecebida]);
        }
        if (stompClient) {
            inscrever(`/livro/${isbn}/chat`, acaoNovaMensagem);
        }
    }, [mensagens, stompClient])

    const setDefaultMensagem = () => {
        const userCookie = Cookies.get('user');
        const userDecode = decodeURIComponent(userCookie);
        const user = JSON.parse(userDecode);
        const { pessoa } = user;
        const { cpf } = pessoa;
        setMensagem({
            livro: { isbn: isbn },
            remetente: { cpf: cpf },
            mensagem: ""
        })
    }

    const atualizaMensagem = (event) => {
        event.preventDefault();
        const { value } = event.target;
        setMensagem({ ...mensagem, "mensagem": value });
    }

    const submit = async (event) => {
        event.preventDefault();
        enviar(`/editora-livros-api/livro/${isbn}`, mensagem);
        setDefaultMensagem();
    }

    return (
        <>
            <Card>
                {
                    Object.values(mensagens).map((mensagem) => (
                        <Card key={mensagem.id}>
                            <Titulo texto={mensagem.remetente.nome} />
                            <Paragrafo texto={mensagem.mensagem} />
                        </Card>
                    ))
                }
                <form onSubmit={submit}>
                    <TextInput
                        value={mensagem.mensagem}
                        id="mensagem"
                        type="text"
                        placeholder="Digite a sua mensagem aqui..."
                        required={true}
                        onChange={(e) => {
                            atualizaMensagem(e);
                        }}
                    />
                    <Button type="submit">
                        <ForwardToInboxIcon className='h-4 w-auto pr-2' />
                        Enviar mensagem
                    </Button>
                </form>
            </Card>
        </>
    )
}
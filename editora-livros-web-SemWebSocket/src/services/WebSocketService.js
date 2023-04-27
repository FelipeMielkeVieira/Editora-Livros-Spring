import { useEffect } from "react";
import { createContext } from "react";
import { useState } from "react"
import SockJS from "sockjs-client";
import * as Stomp from "stompjs";

export const WebSocketContext = createContext(null);

export const WebSocketService = ({ children }) => {
    const [stompClient, setStompClient] = useState(null);

    useEffect(() => {
        const conectar = () => {
            // Caminho do End-Point
            const socket = new SockJS("http://localhost:8085/editora-livros-api/websocket");
            const stomp = Stomp.over(socket);
            stomp.connect({}, () => {
                setStompClient(stomp);
            }, (erro) => {
                console.log("Erro ao conectar: ", erro)
                setTimeout(() => {
                    console.log("Tentando reconectar...");
                    conectar();
                }, 5000);
            })
        }
        conectar();
    }, [])

    const desconectar = () => {
        if (stompClient) {
            stompClient.disconnect();
        }
    }

    const enviar = (destino, mensagem) => {
        if (stompClient) {
            stompClient.send(destino, {}, JSON.stringify(mensagem));
        } else {
            console.log("Conexão não estabelecida!")
        }
    }

    const inscrever = (caminho, acao) => {
        if(!stompClient.subscriptions[caminho]) {
            stompClient.subscribe(caminho, acao);
        }
    }

    return (
        <WebSocketContext.Provider value={{
            stompClient,
            desconectar,
            enviar,
            inscrever
        }}>
            {children}
        </WebSocketContext.Provider>
    )
}
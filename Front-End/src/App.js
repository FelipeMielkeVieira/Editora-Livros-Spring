import React from 'react';
import './App.css';

import {BrowserRouter, Routes, Route} from 'react-router-dom';

import BarraNavegacao from './components/BarraNavegacao';
import Rodape from './components/Rodape';
import TabelaLivros from './components/Livro/TabelaLivros';
import Home from './components/Home';
import Contato from './components/Contato';
import Livro from './components/Livro/Livro';
import Usuario from "./components/Usuario/Usuario";
import Login from "./components/Login";

function App() {
    return (
        <>
            <BrowserRouter>
                <BarraNavegacao/>
                <div className="container mx-auto mb-20 px-4 sm:px-6 py-6">
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/login" element={<Login/>}/>
                        <Route path="/contato" element={<Contato/>}/>
                        <Route path="/usuario/cadastro" element={<Usuario/>}/>
                        <Route path="/usuario/editar:cpf" element={<Usuario/>}/>
                        <Route path="/livros" element={<TabelaLivros/>}/>
                        <Route path="/livro/cadastro" element={<Livro/>}/>
                        <Route path="/livro/editar/:isbn" element={<Livro/>}/>
                    </Routes>
                </div>
                <Rodape/>
            </BrowserRouter>
        </>
    );
}

export default App;

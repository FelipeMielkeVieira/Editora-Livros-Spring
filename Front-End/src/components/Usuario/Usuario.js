import React, {useEffect, useState} from "react";
import {Button, Card, Label, TextInput, Checkbox, Select, Spinner} from "flowbite-react";
import PessoasService from "../../services/PessoasService";


const Usuario = () => {

    const pessoaDados = {
        cpf: "",
        nome: "",
        sobrenome: "",
        email: "",
        senha: "",
        genero: ""
    }
    const [confSenha, setConfSenha] = useState("")
    const [pessoa, setpessoa] = useState(pessoaDados)
    const [carregando, setCarregando] = useState(false)

    const submit = () => {
        console.log("Oi")
    }

    function retornar() {
        console.log("Fui")
    }

    let atualizarPessoa = (event) => {
        setpessoa({
            ...pessoa,
            [event.target.name]: event.target.value
        })
    };

    const [listaGenero, setListaGenero] = useState([])

    useEffect(() => {
        async function start() {
            setCarregando(true)
            try {
                const responseGenero = await PessoasService.getGeneros()
                const dataGenero = await responseGenero.data
                setListaGenero(dataGenero)
            } catch (error) {
                console.log(error)
            }
            setCarregando(false)
        }

        start()
    }, [])

    if (carregando) {
        return (
            <div className="flex justify-center items-center h-80">
                <Spinner />
            </div>
        )
    }
    return (
        <>
            {/*<div>*/}
            {/*    <MeuToast toastDados={dados} />*/}
            {/*</div>*/}
            <Card>
                <form id='formLivro' onSubmit={submit}>
                    <div className="my-2 grid gap-2 grid-cols-2">

                        <div>
                            <Label
                                htmlFor="nome"
                                value="Nome"
                            />
                            <TextInput
                                id="nome"
                                type="text"
                                placeholder="Tony"
                                required={true}
                                name='nome'
                                value={pessoa.nome}
                                onChange={atualizarPessoa}
                            />
                        </div>
                        <div>
                            <Label
                                htmlFor="sobrenome"
                                value="Sobrenome"
                            />
                            <TextInput
                                id="sobrenome"
                                type="text"
                                placeholder="Stark"
                                required={true}
                                name='sobrenome'
                                value={pessoa.sobrenome}
                                onChange={atualizarPessoa}
                            />
                        </div>
                        <div>
                            <Label
                                htmlFor="cpf"
                                value="CPF"
                            />
                            <TextInput
                                id="cpf"
                                type="number"
                                placeholder="1234"
                                required={true}
                                name='cpf'
                                value={pessoa.cpf}
                                onChange={atualizarPessoa}
                            />
                        </div>
                        <div>
                            <Label
                                htmlFor="status"
                                value="Selecione o gênero"
                            />
                            <Select
                                id="genero"
                                required={true}
                                name="genero"
                                value={pessoa.genero}
                                onChange={atualizarPessoa}
                            >
                                {listaGenero.map((genero, index) => (
                                    <option key={index}>
                                        {genero}
                                    </option>
                                ))}
                            </Select>
                        </div>
                    </div>
                    <div>
                        <Label
                            htmlFor="email"
                            value="E-mail"
                        />
                        <TextInput
                            id="email"
                            type="email"
                            placeholder="usuario@dominio.com"
                            required={true}
                            name='email'
                            value={pessoa.email}
                            onChange={atualizarPessoa}
                        />
                    </div>
                    <div className="my-2 grid gap-2 grid-cols-2">
                        <div>
                            <Label
                                htmlFor="senha"
                                value="Senha"
                            />
                            <TextInput
                                id="senha"
                                type="password"
                                placeholder="1234"
                                required={true}
                                name='senha'
                                value={pessoa.senha}
                                onChange={atualizarPessoa}
                            />
                        </div>
                        <div>
                            <Label
                                htmlFor="confSenha"
                                value="Confirmação de Senha"
                            />
                            <TextInput
                                id="confSenha"
                                type="password"
                                placeholder="1234"
                                required={true}
                                name='confSenha'
                                value={confSenha}
                                onChange={(event) => setConfSenha(event.target.value)}
                            />
                        </div>
                    </div>
                    <div className="flex items-center gap-2">
                        <Checkbox id="agree"/>
                        <Label htmlFor="agree">
                            Li e estou de acordo com os {' '}
                            <a
                                href="/termos"
                                className="text-blue-600 hover:underline dark:text-blue-500"
                            >
                                termos e condições
                            </a>
                        </Label>
                    </div>
                    <div className="mt-8 grid gap-10 grid-cols-2">
                        <Button type="submit">
                            Cadastrar
                        </Button>
                        <Button color="light" onClick={retornar}>
                            Voltar
                        </Button>
                    </div>
                </form>
            </Card>
        </>
    )
}
export default Usuario
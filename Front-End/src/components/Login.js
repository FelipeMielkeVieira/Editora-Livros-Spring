import React, {useState} from 'react'
import {Button, Label, TextInput} from 'flowbite-react'
import AutenticacaoService from '../services/AutenticacaoService';

const Login = () => {

    const [user, setUser] = useState({
        email: '',
        senha: ''
    });

    const atualizarUsuario = (event) => {
        setUser({
            ...user, [event.target.name]: event.target.value
        })
        console.log(user);
    }

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await AutenticacaoService.login(user);
            console.log(response);
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div className="w-full max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow sm:p-6 md:p-8">
            <form className="flex flex-col gap-4" onSubmit={handleLogin}>
                <div>
                    <div className="mb-2 block">
                        <Label
                            htmlFor="email"
                            value="E-mail"
                        />
                    </div>
                    <TextInput
                        id="email"
                        type="email"
                        placeholder="usuario@dominio.com"
                        name='email'
                        value={user.email}
                        required={true}
                        onChange={atualizarUsuario}
                    />
                </div>
                <div>
                    <div className="mb-2 block">
                        <Label
                            htmlFor="senha"
                            value="Senha"
                        />
                    </div>
                    <TextInput
                        id="senha"
                        type="password"
                        name='senha'
                        value={user.senha}
                        required={true}
                        onChange={atualizarUsuario}
                    />
                </div>
                <Button type="submit">
                    Login
                </Button>
                <Button href={"https://localhost:8443/oauth2/authorization/google"}>
                    Google
                </Button>
            </form>
        </div>)

}
export default Login
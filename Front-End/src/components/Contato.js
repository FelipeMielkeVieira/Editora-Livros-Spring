import React, { Component } from 'react'
import { Label, TextInput, Button, Textarea } from 'flowbite-react'
import { PaperAirplaneIcon } from '@heroicons/react/24/outline'

export default class Contato extends Component {
    render() {
        return (
            <>
                <div className="w-full h-auto bg-white border border-gray-200 rounded-lg shadow sm:p-6 md:p-8">
                    <form className="space-y-2" action="#">
                        <h5 className="text-xl font-medium text-gray-900">Envie-nos uma mensagem</h5>
                        <div>
                            <Label
                                htmlFor="email"
                                value="Informe o seu email"
                            />
                            <TextInput
                                id="email"
                                type="email"
                                placeholder="usuario@dominio.com"
                                required={true}
                            />
                        </div>
                        <div>
                            <Label
                                htmlFor="assunto"
                                value="Assunto"
                            />
                            <TextInput
                                id="assunto"
                                type="text"
                                placeholder="Dê um assunto para a sua mensagem"
                                required={true}
                            />
                        </div>
                        <div>
                            <Label
                                htmlFor="mensagem"
                                value="Mensagem"
                            />
                            <Textarea
                                id="mensagem"
                                placeholder="Digite a sua mensagem aqui..."
                                required={true}
                                rows={3}
                            />
                        </div>
                        <Button type="submit">
                            <PaperAirplaneIcon className='h-4 w-auto pr-2'/>
                            Enviar mensagem
                        </Button>
                    </form>
                </div>
            </>
        )
    }
}
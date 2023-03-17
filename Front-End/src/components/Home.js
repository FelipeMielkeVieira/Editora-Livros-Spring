import React, { Component } from 'react'
import { Card } from 'flowbite-react'

class Home extends Component {
  render() {
    return (
      <>
        <Card>
          <h5 className="text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
            Bem vindo!
          </h5>
          <p className="font-normal text-gray-700 dark:text-gray-400">
            Seja bem-vindo à Editora de Livros, uma plataforma inovadora para autores publicarem seus livros. Aqui, oferecemos aos escritores a oportunidade de tornar seu trabalho disponível ao público sem precisar passar pelos processos tradicionais de publicação.
          </p>
          <p className="font-normal text-gray-700 dark:text-gray-400">
            Nós acreditamos que cada história é única e merece ser contada. Por isso, oferecemos aos autores um processo simples e acessível para publicar e compartilhar seus livros com o mundo. Nossa equipe de especialistas está sempre à disposição para ajudar e orientar os autores em todas as etapas do processo de publicação.
          </p>
          <p className="font-normal text-gray-700 dark:text-gray-400">
            Não perca mais tempo, torna-se parte da nossa comunidade de escritores hoje mesmo e publique o seu livro com a Editora de Livros. Juntos, vamos compartilhar histórias incríveis e inspiradoras com o mundo.
          </p>
        </Card>
      </>
    )
  }
}

export default Home
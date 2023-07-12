<h2 align='center'>Sistema de gerenciamento de hotel - Capri Hotel</h2>

<p align='center'>
  <a href='#-proposta'>Proposta</a> |
  <a href='#-preview'>Preview</a> |
  <a href='#-estrutura'>Estrutura</a> |
  <a href='#-funcionalidades'>Funcionalidades</a> |
  <a href='#-tecnologias'>Tecnologias</a> |
<a href='#-projeto'>Projeto</a>
<p>
<hr>

## 🧾 Proposta
Esse projeto foi idealizado para realizar o gerenciamento de um sistema de hotel, seguindo o conceito CRUD.
A aplicação possibilita a criação, edição, listagem e exclusão de reservas e hóspedes.
A marca utilizada é fictícia e foi criada por mim para compor a parte visual do projeto. 
A inspiração para a construção dessa aplicação foi o [Challenge ONE - Hotel Alura](https://github.com/alura-challenges/challenge-one-alura-hotel-br), proposto durante a formação ONE - Oracle Next Education.
<hr>

## 🎥 Preview


https://github.com/GusBuz/capri_hotel/assets/116600311/f8b11a1d-cc05-4dea-afb5-f788d757bf47


<hr>

## 🧱 Estrutura

A aplicação é um projeto WebServlet, feito com JPA e Hibernate para a persistência e o H2 database para a criação de um banco de dados em memória. 
JSP, CSS e JS foram usados para a parte visual e dinâmica da aplicação. Para a criação do logotipo, utilizei Adobe Illustrator, e as ilustrações e ícones foram adquiridas no [Manypixels](https://www.manypixels.co/gallery) e [SvgRepo](https://www.svgrepo.com/).
<hr>

## ⚙ Funcionalidades

O site apresenta as funções necessárias para a criação de reservas e cadastros de hóspedes em um sistema de hotel.
Primeiramente o usuário, que idealmente seria um funcionário da recepção do hotel, deve fazer login com suas credenciais.
Para criar um novo registro, é necessário informar os dados do período da reserva, assim como os do hóspede.
Na sessão de busca, é possível visualizar as listas de reservas e hóspedes, e buscar um registro específico utilizando os filtros.  Também é possível editar ou excluir um registro.
Só existe uma credencial de acesso configurada, com usuário `admin` e senha `admin`.
<hr>

## 💻 Tecnologias

As tecnologias usadas nesse projeto foram:

- `Linguagem`: Java 19
- `IDE`: IntelliJ IDEA
- `Frontend`: JSP, HTML e CSS
- `Gerenciador`: Maven
- `Servidor`: TomCat 10
- `Dependências`: Jakarta Servlet, JPA, Hibernate, TagLib, Jakarta EL, H2 e GSON
<hr>

## 💾 Projeto

Para acessar a aplicação é só baixar o arquivo WAR do projeto [aqui](https://github.com/GusBuz/capri_hotel/raw/main/github/capri_hotel.war) e executar num servidor Java. 

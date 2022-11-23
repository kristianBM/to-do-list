import React from 'react';
import { Navbar, Nav, Container } from 'react-bootstrap';

function MainNav() {

  return (

    <Navbar bg="light" variant="light">
      <Container>
        <Navbar.Brand href="#home">.to<strong>do</strong>list</Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="/cadastrar">Nova Tarefa</Nav.Link>
          <Nav.Link href="/docs">Docs</Nav.Link>
        </Nav>
      </Container>
    </Navbar>

  )
}

export default MainNav;
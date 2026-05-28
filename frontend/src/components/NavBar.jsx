import React from "react";
import { Link } from 'react-router-dom';

function Navbar(){
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-secondary shadow-sm">
            <div className="container">
                <Link className="navbar-brand fw-bold" to="/">
                    Cursos - SENAI
                </Link>
                <button className="navbar-toggler" type="button">
                    <span className="navbar-toogler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav ms-auto">
                        <li className="nav-item">
                            <Link className="nav-link" to="/">Ver cursos</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/novo">Cadastro de novo curso</Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}

export default Navbar;
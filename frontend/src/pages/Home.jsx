import React from "react";
import { Link } from 'react-router-dom';

function Home() {
    return (
        <div className="container mt-5">
            <div className="d-flex justify-content-between align-items-center mb-4">
                <h2>Catálogo de cursos</h2>
                <Link to="/novo" className="btn btn-primary">
                    + Adiconar Curso
                </Link>
            </div>

            <div className="row">
                <div className="col-md-4 mb-4">
                    <div className="card h-100 shadow-sm border-0">
                        <div className="card-body">
                            <h5 className="card-title text-secondary fw-bold">Desenvolvimento IPI - SENAI</h5>
                            <p className="card-text text-muted">
                                Aprenda a criar interface modernas parecidos com o Codepen
                            </p>
                        </div>
                        <div className="card-footer bg-white border-0">
                            <button className="btn btn-outline-secondary">Ver detalhes</button>
                        </div>
                    </div>
                </div>

                <div className="col-md-4 mb-4">
                    <div className="card h-100 shadow-sm border-0">
                        <div className="card-body">
                            <h5 className="card-title text-secondary fw-bold">Desenvolvimento Jogos</h5>
                            <p className="card-text text-muted">
                               Desenvolva jogos para plataforma com three.js e p5.js                             
                            </p>
                        </div>
                        <div className="card-footer bg-white border-0">
                            <button className="btn btn-outline-secondary">Ver detalhes</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
}

export default Home;
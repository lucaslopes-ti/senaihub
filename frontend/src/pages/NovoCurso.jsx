import React from "react";
import { Link } from 'react-router-dom';

function NovoCurso() {
    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-8">
                    <div className="card shadow-sm border-0">
                        <div className="card-header bg-white text-secondary">
                            <h4 className="mb-8 fw-bold">Cadastrar Novo curso</h4>
                        </div>

                        <div className="card-body">
                            <form>
                                <div className="mb-3">
                                    <label className="form-label fw-bold">Nome do curso</label>
                                    <input type="text" className="form-control" placeholder="exemplo"></input>
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-bold">Descrição</label>
                                    <input type="text" className="form-control" placeholder="exemplo"></input>
                                </div>

                                <div className="mb-3">
                                    <label className="form-label fw-bold">Carga Horária</label>
                                    <input type="text" className="form-control" placeholder="exemplo"></input>
                                </div>

                                <div className="d-flex justify-content-end mt-4">
                                    <Link to="/" className="btn btn-white">
                                        Cancelar
                                    </Link>
                                    <button type="button" className="btn btn-primary">
                                        Salvar
                                    </button>
                                </div>


                            </form>
                        </div>
                    </div>
                
                </div>
            </div>
        </div>
    );
}

export default NovoCurso;
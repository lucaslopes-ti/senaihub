import React from "react";
import { Link } from 'react-router-dom';

function Home() {
    return (
        <div className="tech-home">
            <header className="tech-navbar">
                <div className="container d-flex align-items-center justify-content-between">
                    <div className="tech-brand">
                        <span className="tech-brand-mark">S</span>
                        <div>
                            <div className="tech-brand-title">SENAI HUB</div>
                            <div className="tech-brand-subtitle">Institucional Tech</div>
                        </div>
                    </div>
                    <nav className="tech-nav-links d-none d-md-flex" aria-label="Navegacao principal">
                        <a href="#hero">Visao</a>
                        <a href="#destaques">Destaques</a>
                        <a href="#footer">Contato</a>
                    </nav>
                    <div className="d-flex gap-2">
                        <a href="#destaques" className="btn btn-outline-light btn-sm">
                            Destaques
                        </a>
                        <Link to="/novo" className="btn btn-primary btn-sm">
                            Cadastrar curso
                        </Link>
                    </div>
                </div>
            </header>

            <section id="hero" className="tech-hero">
                {/* Carousel do Bootstrap precisa do JS do Bootstrap para animar */}
                <div
                    id="heroCarousel"
                    className="carousel slide tech-hero-carousel"
                    data-bs-ride="carousel"
                    data-bs-interval="5200"
                >
                    <div className="carousel-indicators">
                        <button
                            type="button"
                            data-bs-target="#heroCarousel"
                            data-bs-slide-to="0"
                            className="active"
                            aria-current="true"
                            aria-label="Slide 1"
                        ></button>
                        <button
                            type="button"
                            data-bs-target="#heroCarousel"
                            data-bs-slide-to="1"
                            aria-label="Slide 2"
                        ></button>
                        <button
                            type="button"
                            data-bs-target="#heroCarousel"
                            data-bs-slide-to="2"
                            aria-label="Slide 3"
                        ></button>
                    </div>
                    <div className="carousel-inner">
                        <div className="carousel-item active">
                            <div className="tech-hero-slide tech-hero-slide--one">
                                <div className="container">
                                    <div className="row align-items-center g-4">
                                        <div className="col-lg-7 tech-hero-content">
                                            <span className="tech-pill">
                                                SENAI HUB | Plataforma Institucional
                                            </span>
                                            <h1 className="display-5 fw-bold tech-hero-title">
                                                Formacao tech com foco em impacto real
                                            </h1>
                                            <p className="lead tech-hero-subtitle">
                                                Esta e a vitrine oficial do SENAI HUB: cursos objetivos, linguagem
                                                institucional e resultados que aparecem na pratica.
                                            </p>
                                            <div className="d-flex gap-2 flex-wrap">
                                                <a href="#destaques" className="btn btn-light">
                                                    Ver destaques
                                                </a>
                                                <button className="btn btn-outline-light">Baixar catalogo</button>
                                            </div>
                                        </div>
                                        <div className="col-lg-5">
                                            <div className="tech-hero-panel">
                                                <div className="tech-hero-panel-title">Indicador</div>
                                                <div className="tech-hero-panel-number">+1.200</div>
                                                <p className="mb-0">alunos formados com foco em empregabilidade.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="carousel-item">
                            <div className="tech-hero-slide tech-hero-slide--two">
                                <div className="container">
                                    <div className="row align-items-center g-4">
                                        <div className="col-lg-7 tech-hero-content">
                                            <span className="tech-pill">Trilha 01 | Engenharia</span>
                                            <h2 className="display-5 fw-bold tech-hero-title">
                                                Engenharia de Software Aplicada
                                            </h2>
                                            <p className="lead tech-hero-subtitle">
                                                Padroes, testes e governanca para entregar com previsibilidade.
                                            </p>
                                            <div className="d-flex gap-2 flex-wrap">
                                                <button className="btn btn-light">Detalhes da trilha</button>
                                                <button className="btn btn-outline-light">Ver matriz</button>
                                            </div>
                                        </div>
                                        <div className="col-lg-5">
                                            <div className="tech-hero-panel">
                                                <div className="tech-hero-panel-title">Carga horaria</div>
                                                <div className="tech-hero-panel-number">160h</div>
                                                <p className="mb-0">projetos guiados e entregas semanais.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="carousel-item">
                            <div className="tech-hero-slide tech-hero-slide--three">
                                <div className="container">
                                    <div className="row align-items-center g-4">
                                        <div className="col-lg-7 tech-hero-content">
                                            <span className="tech-pill">Trilha 02 | Dados</span>
                                            <h2 className="display-5 fw-bold tech-hero-title">
                                                Dados e Inteligencia Analitica
                                            </h2>
                                            <p className="lead tech-hero-subtitle">
                                                Dashboards e dados confiaveis para decisao rapida.
                                            </p>
                                            <div className="d-flex gap-2 flex-wrap">
                                                <button className="btn btn-light">Detalhes da trilha</button>
                                                <button className="btn btn-outline-light">Ver matriz</button>
                                            </div>
                                        </div>
                                        <div className="col-lg-5">
                                            <div className="tech-hero-panel">
                                                <div className="tech-hero-panel-title">Projetos</div>
                                                <div className="tech-hero-panel-number">08</div>
                                                <p className="mb-0">cases completos para portifolio.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button
                        className="carousel-control-prev"
                        type="button"
                        data-bs-target="#heroCarousel"
                        data-bs-slide="prev"
                    >
                        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Anterior</span>
                    </button>
                    <button
                        className="carousel-control-next"
                        type="button"
                        data-bs-target="#heroCarousel"
                        data-bs-slide="next"
                    >
                        <span className="carousel-control-next-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Proximo</span>
                    </button>
                </div>
            </section>

            <section id="destaques" className="tech-highlights">
                <div className="container">
                    <div className="tech-section-header">
                        <h2 className="tech-section-title">Destaques institucionais</h2>
                        <p className="tech-section-subtitle">
                            Conteudo objetivo e autoridade tecnica para reforcar a marca SENAI HUB.
                        </p>
                    </div>
                    <div className="row g-4">
                        <div className="col-md-4">
                            <div className="tech-spotlight-card">
                                <div className="tech-spotlight-top">
                                    <span className="tech-spotlight-icon">01</span>
                                    <span className="tech-spotlight-tag">Governanca</span>
                                </div>
                                <h5>Padrao de qualidade</h5>
                                <p>Metodologias padronizadas e indicadores claros de performance.</p>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="tech-spotlight-card">
                                <div className="tech-spotlight-top">
                                    <span className="tech-spotlight-icon">02</span>
                                    <span className="tech-spotlight-tag">Industria</span>
                                </div>
                                <h5>Conexao com o mercado</h5>
                                <p>Conteudos alinhados a demandas reais e projetos praticos.</p>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="tech-spotlight-card">
                                <div className="tech-spotlight-top">
                                    <span className="tech-spotlight-icon">03</span>
                                    <span className="tech-spotlight-tag">Resultados</span>
                                </div>
                                <h5>Impacto mensuravel</h5>
                                <p>Relatorios objetivos e entregas praticas para as turmas.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <footer id="footer" className="tech-footer">
                <div className="container">
                    <div className="tech-footer-content">
                        <div>
                            <strong>SENAI HUB</strong>
                            <p className="mb-0">Tecnologia aplicada com visao institucional.</p>
                        </div>
                        <div className="tech-footer-links">
                            <a href="#hero">Voltar ao topo</a>
                            <button className="btn btn-outline-light btn-sm">Falar com o time</button>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    );
}

export default Home;
import React from "react";
import { BrowserRouter, Router, Route, Routes } from "react-router-dom";

import Navbar from "./components/NavBar";
import Home from "./pages/Home";
import NovoCurso from "./pages/NovoCurso";

function App() {
  return (
    <BrowserRouter>
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/novo" element={<NovoCurso />}></Route> 
      </Routes>
    
    </BrowserRouter>
  );
}

export default App;
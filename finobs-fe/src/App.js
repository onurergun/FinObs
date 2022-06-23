import "./app.css";
import {Helmet} from "react-helmet"
import {BrowserRouter as Router, Routes, Route} from "react-router-dom"

import Footer from "./components/Footer";
import NavBar from "./components/NavBar";
import Home from "./pages/Home";
import SignUp from "./pages/SignUp";
import Login from "./pages/Login";
import Error from "./pages/Error";


function App() {
  return (
    <Router>
        <Helmet>
            <style>{'body { background-color: #F8F9FA; }'}</style>
        </Helmet>
        <NavBar></NavBar>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="/login" element={<Login />} />
            <Route path="*" element={<Error />} />
        </Routes>
        <Footer></Footer>
    </Router>
  );
}

export default App;

import "./app.css";
import {Helmet} from "react-helmet"

import Footer from "./components/Footer";
import NavBar from "./components/NavBar";
import SignUp from "./pages/SignUp";


function App() {
  return (
    <div>
        <Helmet>
            <style>{'body { background-color: #F8F9FA; }'}</style>
        </Helmet>
        <NavBar></NavBar>
        <SignUp></SignUp>
        <Footer></Footer>
    </div>
  );
}

export default App;

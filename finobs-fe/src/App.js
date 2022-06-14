import SignUp from "./pages/SignUp";
import NavBar from "./components/NavBar";
import {Helmet} from "react-helmet"

import "./app.css";

function App() {
  return (
    <div>
        <Helmet>
            <style>{'body { background-color: #F8F9FA; }'}</style>
        </Helmet>
        <NavBar></NavBar>
        <SignUp></SignUp>
    </div>
  );
}

export default App;

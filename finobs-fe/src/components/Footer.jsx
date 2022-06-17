import React from 'react';
import "./footer.css"

function Footer() {
    return (
        <footer className="footer mt-auto py-4 bg-light text-center footer-parent">
            <div className="container">
                <span className="text-muted">
                    © FinObs © {new Date().getFullYear()}
                </span>
                <br></br>
                <em className="text-black-50">
                    Designed by Onur Ergün
                </em>
            </div>
        </footer>
    );
}

export default Footer;
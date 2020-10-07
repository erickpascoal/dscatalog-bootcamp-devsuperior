import React from 'react';
import { NavLink } from 'react-router-dom';
import './styles.scss'
// import { Container } from './styles';

const NavBar: React.FC = () => {
    return (
        <nav className="navbar-container bg-primary" >
            <div className="col-2 logo-name">
                <h4>DSCATALOG</h4>
            </div>

            <div className="col-6 offset-2">
                <ul className="d-flex">
                    <li>
                        Home
                    </li>
                    <li>
                        Catalog
                    </li>
                    <li>
                        Admin
                    </li>
                </ul>
            </div>
        </nav>


    );
}

export default NavBar;
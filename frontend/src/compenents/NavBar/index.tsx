import React from 'react';
import { NavLink, Link } from 'react-router-dom';
import './styles.scss'
// import { Container } from './styles';

const Navbar: React.FC = () => {
    return (
        <nav className="row bg-primary navbar-container" >
            <div className="col-2">
                <Link to="/" className="nav-logo-text">
                    <h4>DS CATALOG</h4>
                </Link>
            </div>

            <div className="col-6 offset-2">
                <ul className="main-menu">
                    <li>
                        <NavLink to="/" exact activeClassName="active">
                            Home
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/catalog" activeClassName="active">
                            Catálogo
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/admin" activeClassName="active">
                            Admin
                        </NavLink>
                    </li>
                </ul>
            </div>
        </nav>


    );
}

export default Navbar;
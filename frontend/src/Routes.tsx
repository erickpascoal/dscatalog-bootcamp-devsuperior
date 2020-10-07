import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import Navbar from './compenents/Navbar';
import Admin from './pages/Admin';
import Catalog from './pages/Catalog';
import Home from './pages/Home';

const Routes = () => {
    return (
        <BrowserRouter>
            <Navbar />
            <Switch>
                <Route path="/" exact component={Home} />
                <Route path="/catalog" component={Catalog} />
                <Route path="/admin" component={Admin} />
            </Switch>
        </BrowserRouter>
    );
}

export default Routes;
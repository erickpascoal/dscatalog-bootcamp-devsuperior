import React from 'react';
import './styles.scss';

import { ReactComponent as HomeImage } from '../../assets/images/home.svg'
import ButtonIcon from '../../compenents/ButtoIcon';
import { Link } from 'react-router-dom';

const Home: React.FC = () => {
    return (
        <div className="home-container">

            <div className="row home-content">
                <div className="col-6 home-text" >
                    <h1 className="text-title">Conheça o melhor <br /> catálogo de produtos</h1>
                    <p className="text-subtitle">Ajudaremos você a encontrar os melhores <br /> produtos disponíveis no mercado.</p>
                    <Link to="catalog">
                        <ButtonIcon text="INICIE AGORA A SUA BUSCA" />
                    </Link>
                </div>
                <div className="col-6">
                    <HomeImage className="home-image" />
                </div>
            </div>

        </div>
    );
}

export default Home;
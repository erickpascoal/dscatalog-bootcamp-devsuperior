import React from 'react';
import './styles.scss'
import { ReactComponent as ArrowRightImage } from '../../assets/images/arrow-right.svg'

type Props = {
    text: string
}

const ButtonIcon = ({ text }: Props) => {
    return (
        <div className="d-flex">
            <button className="btn btn-primary btn-icon">
                {text}
            </button>
            <div className="btn-icon-content">
                <ArrowRightImage />
            </div>
        </div>
    );
}

export default ButtonIcon;
import React from 'react';
import style from './style.scss'
import AddIcon from './../../../res/img/honey.svg'
import BeeIcon from './../../../res/img/bee.svg'


export default class PopupMessage extends React.Component {

	render() {
		return (
			<div onClick={this.props.changeErrorStatus} className="popup">
				{this.props.message}
			</div>
		)
	}
}


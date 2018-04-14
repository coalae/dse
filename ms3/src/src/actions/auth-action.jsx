import axios from "axios"

import {REG_API} from "../constants/rest-api"
import {REG_SUCCESS, REG_FAILED, LOGIN_SUCCESS, LOGIN_FAILED} from "../constants/auth-constants"


export function registration(username, password, firstname, lastname, city) {
	return function (dispatch) {
		return axios.get(REG_API + 'users/register?username=' + username +
			'&password=' + password +
			'&firstname=' + firstname + '' +
			' &lastname=' + lastname +
			'&city=' + city)
			.then((res) => {
				dispatch({type: REG_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: REG_FAILED, payload: err})
			})
	}
}

export function login(username, password) {
	return function (dispatch) {
		return axios.post(REG_API + 'users/loginMS3', {}, {
			headers: {username:  username, password: password}
		})
			.then((res) => {
				dispatch({type: LOGIN_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: LOGIN_FAILED, payload: err})
			})
	}
}

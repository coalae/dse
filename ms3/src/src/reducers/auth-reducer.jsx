import {REG_FAILED, REG_SUCCESS, LOGIN_FAILED, LOGIN_SUCCESS} from "../constants/auth-constants"

export function reg(state =
											{
												text: null,
												done: false,
												error: null,
												success: null
											}
	, action) {
	switch (action.type) {
		case REG_FAILED: {
			return {
				...state,
				error: action.payload,
				done: false,
				success: false
			}
		}
		case REG_SUCCESS: {
			return {
				...state,
				done: true,
				text: action.payload,
				success: true
			}
		}
		default: {
			return {...state}
		}
	}
}

export function login(state =
											{
												user: null,
												done: false,
												error: null
											}
	, action) {
	switch (action.type) {
		case LOGIN_FAILED: {
			return {
				...state,
				error: action.payload,
				done: false
			}
		}
		case LOGIN_SUCCESS: {
			return {
				...state,
				done: true,
				user: action.payload
			}
		}
		default: {
			return {...state}
		}
	}
}

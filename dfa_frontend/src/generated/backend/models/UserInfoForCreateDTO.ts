/* tslint:disable */
/* eslint-disable */
/**
 * App API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: snapshot
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { exists, mapValues } from '../runtime';
/**
 * Info for create new account.
 * @export
 * @interface UserInfoForCreateDTO
 */
export interface UserInfoForCreateDTO {
    /**
     * Username.
     * @type {string}
     * @memberof UserInfoForCreateDTO
     */
    username: string;
    /**
     * Password.
     * @type {string}
     * @memberof UserInfoForCreateDTO
     */
    password: string;
    /**
     * Users email.
     * @type {string}
     * @memberof UserInfoForCreateDTO
     */
    email?: string;
    /**
     * Ethereum address.
     * @type {string}
     * @memberof UserInfoForCreateDTO
     */
    address?: string;
    /**
     * Ethereum private key.
     * @type {string}
     * @memberof UserInfoForCreateDTO
     */
    privateKey?: string;
}

export function UserInfoForCreateDTOFromJSON(json: any): UserInfoForCreateDTO {
    return UserInfoForCreateDTOFromJSONTyped(json, false);
}

export function UserInfoForCreateDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): UserInfoForCreateDTO {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'username': json['username'],
        'password': json['password'],
        'email': !exists(json, 'email') ? undefined : json['email'],
        'address': !exists(json, 'address') ? undefined : json['address'],
        'privateKey': !exists(json, 'privateKey') ? undefined : json['privateKey'],
    };
}

export function UserInfoForCreateDTOToJSON(value?: UserInfoForCreateDTO | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'username': value.username,
        'password': value.password,
        'email': value.email,
        'address': value.address,
        'privateKey': value.privateKey,
    };
}



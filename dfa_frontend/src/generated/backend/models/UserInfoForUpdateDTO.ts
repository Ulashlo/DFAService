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
 * User info for update dto.
 * @export
 * @interface UserInfoForUpdateDTO
 */
export interface UserInfoForUpdateDTO {
    /**
     * Users email.
     * @type {string}
     * @memberof UserInfoForUpdateDTO
     */
    email?: string;
    /**
     * Ethereum address.
     * @type {string}
     * @memberof UserInfoForUpdateDTO
     */
    address?: string;
    /**
     * Ethereum private key.
     * @type {string}
     * @memberof UserInfoForUpdateDTO
     */
    privateKey?: string;
}

export function UserInfoForUpdateDTOFromJSON(json: any): UserInfoForUpdateDTO {
    return UserInfoForUpdateDTOFromJSONTyped(json, false);
}

export function UserInfoForUpdateDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): UserInfoForUpdateDTO {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'email': !exists(json, 'email') ? undefined : json['email'],
        'address': !exists(json, 'address') ? undefined : json['address'],
        'privateKey': !exists(json, 'privateKey') ? undefined : json['privateKey'],
    };
}

export function UserInfoForUpdateDTOToJSON(value?: UserInfoForUpdateDTO | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'email': value.email,
        'address': value.address,
        'privateKey': value.privateKey,
    };
}



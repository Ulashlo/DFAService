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
import {
    UserLinkDTO,
    UserLinkDTOFromJSON,
    UserLinkDTOFromJSONTyped,
    UserLinkDTOToJSON,
} from './';

/**
 * Info for request for issuer rights.
 * @export
 * @interface IssuerRequestDTO
 */
export interface IssuerRequestDTO {
    /**
     * Id of request.
     * @type {string}
     * @memberof IssuerRequestDTO
     */
    id: string;
    /**
     * 
     * @type {UserLinkDTO}
     * @memberof IssuerRequestDTO
     */
    userLink: UserLinkDTO;
    /**
     * Date when request was created.
     * @type {Date}
     * @memberof IssuerRequestDTO
     */
    dateCreated: Date;
}

export function IssuerRequestDTOFromJSON(json: any): IssuerRequestDTO {
    return IssuerRequestDTOFromJSONTyped(json, false);
}

export function IssuerRequestDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): IssuerRequestDTO {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'id': json['id'],
        'userLink': UserLinkDTOFromJSON(json['userLink']),
        'dateCreated': (new Date(json['dateCreated'])),
    };
}

export function IssuerRequestDTOToJSON(value?: IssuerRequestDTO | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'id': value.id,
        'userLink': UserLinkDTOToJSON(value.userLink),
        'dateCreated': (value.dateCreated.toISOString()),
    };
}



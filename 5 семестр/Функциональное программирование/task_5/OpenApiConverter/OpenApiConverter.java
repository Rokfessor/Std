/*
 * Copyright (c) 2008-2021, Compass Plus Limited. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. This Source Code is distributed
 * WITHOUT ANY WARRANTY; including any implied warranties but not limited to
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Mozilla Public License, v. 2.0. for more details.
 */

package org.radixware.kernel.common.defs.ads.openapi.utils.converter;

import org.radixware.kernel.common.defs.ads.openapi.model.*;
import org.radixware.kernel.common.json.IJsonDocument;

import java.util.List;
import java.util.Map;

public class OpenApiConverter {
    public OpenApiConverter() {

    }

    public IJsonDocument convertOpenApiSchema(OpenApiSchema schema) {
        IJsonDocument result = IJsonDocument.Factory.makeObject(); // 3.0.1 Schema
        if (schema == null)
            return result;

        System.err.println("======\n" + schema.document.copy().toJsonString() + "======\n");

        IJsonDocument doc = schema.document;

        setString(doc, result, OpenApiSchema.OPENAPI_FIELD);
        setDocument(doc, result, OpenApiSchema.INFO_FIELD, () -> convertInfo(schema.info));
        setString(doc, result, OpenApiSchema.JSON_SCHEMA_DIALECT_FIELD, extensionName(OpenApiSchema.JSON_SCHEMA_DIALECT_FIELD));
        setDocument(doc, result, OpenApiSchema.SERVERS_FIELD,
                () -> convertObjectArray(schema.servers, this::convertServer));
        setDocument(doc, result, OpenApiSchema.PATHS_FIELD,
                () -> convertMap(schema.paths, obj -> convertObjectReference(obj, this::convertPath)));
        return result;
    }

    public IJsonDocument convertPath(OpenApiPath path) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (path == null)
            return result;

        IJsonDocument doc = path.document;

        setString(doc, result, OpenApiPath.REF_FIELD);
        setString(doc, result, OpenApiPath.SUMMARY_FIELD);
        setString(doc, result, OpenApiPath.DESCRIPTION_FIELD);
        setDocument(doc, result, OpenApiPath.GET_FIELD, () -> convertOperation(path.get));
        setDocument(doc, result, OpenApiPath.PUT_FIELD, () -> convertOperation(path.put));
        setDocument(doc, result, OpenApiPath.POST_FIELD, () -> convertOperation(path.post));
        setDocument(doc, result, OpenApiPath.DELETE_FIELD, () -> convertOperation(path.delete));
        setDocument(doc, result, OpenApiPath.OPTIONS_FIELD, () -> convertOperation(path.options));
        setDocument(doc, result, OpenApiPath.HEAD_FIELD, () -> convertOperation(path.head));
        setDocument(doc, result, OpenApiPath.PATCH_FIELD, () -> convertOperation(path.patch));
        setDocument(doc, result, OpenApiPath.TRACE_FIELD, () -> convertOperation(path.trace));
        setDocument(doc, result, OpenApiSchema.SERVERS_FIELD,
                () -> convertObjectArray(path.servers, this::convertServer));
        setDocument(doc, result, OpenApiPath.PARAMETERS_FIELD,
                () -> convertObjectArray(path.parameters,
                        obj -> convertObjectReference(obj, this::convertParameter)
                )
        );

        return result;
    }

/*    public IJsonDocument convertOperationRef(OpenApiReference<OpenApiOperation> operation) {
        if (operation == null)
            return IJsonDocument.Factory.makeObject();

        if (operation.isRef())
            return convertReference(operation);

        return convertOperation(operation.get());
    }*/

    public IJsonDocument convertOperation(OpenApiOperation operation) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (operation == null)
            return result;

        IJsonDocument doc = operation.document;

        setDocument(doc, result, OpenApiOperation.TAGS_FILED, () -> convertStringArray(operation.tags));
        setString(doc, result, OpenApiOperation.SUMMARY_FILED);
        setString(doc, result, OpenApiOperation.DESCRIPTION_FILED);
        setDocument(doc, result, OpenApiOperation.EXTERNAL_DOCS_FILED, () -> convertExternalDocs(operation.externalDocs));
        setString(doc, result, OpenApiOperation.OPERATION_ID_FILED);
        setDocument(doc, result, OpenApiOperation.PARAMETERS_FILED,
                () -> convertObjectArray(operation.parameters,
                        obj -> convertObjectReference(obj, this::convertParameter)
                )
        );
        setDocument(doc, result, OpenApiOperation.REQUEST_BODY_FILED,
                () -> convertObjectReference(operation.requestBody, this::convertRequestBody)
        );

        return result;
    }

    public <T> IJsonDocument convertObjectReference(OpenApiReference<T> objRef, ConvertObjectFunction<T> function) {
        if (objRef == null)
            return IJsonDocument.Factory.makeObject();

        if (objRef.isRef())
            return convertReference(objRef);

        return function.convert(objRef.get());
    }

    public IJsonDocument convertRequestBody(OpenApiRequestBody requestBody) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (requestBody == null)
            return result;

        IJsonDocument doc = requestBody.document;

        return result;
    }

    public IJsonDocument convertParameter(OpenApiParameter parameter) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (parameter == null)
            return result;

        IJsonDocument doc = parameter.document;

        return result;
    }

    public IJsonDocument convertExternalDocs(OpenApiExternalDocumentation extDoc) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (extDoc == null)
            return result;

        IJsonDocument doc = extDoc.document;

        setString(doc, result, OpenApiExternalDocumentation.DESCRIPTION_FIELD);
        setString(doc, result, OpenApiExternalDocumentation.URL_FIELD);

        return result;
    }

    public IJsonDocument convertInfo(OpenApiInfo info) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (info == null)
            return result;

        IJsonDocument doc = info.document;

        setString(doc, result, OpenApiInfo.TITLE_FIELD);
        setString(doc, result, OpenApiInfo.SUMMARY_FIELD, extensionName(OpenApiInfo.SUMMARY_FIELD));
        setString(doc, result, OpenApiInfo.DESCRIPTION_FIELD);
        setString(doc, result, OpenApiInfo.TERMS_OF_SERVICE_FIELD);
        setDocument(doc, result, OpenApiInfo.CONTACT_FIELD, () -> convertContact(info.contact));
        setDocument(doc, result, OpenApiInfo.LICENSE_FIELD, () -> convertLicense(info.license));
        setString(doc, result, OpenApiInfo.VERSION_FIELD);

        return result;
    }

    public IJsonDocument convertServer(OpenApiServer server) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (server == null)
            return result;

        IJsonDocument doc = server.document;

        setString(doc, result, OpenApiServer.URL_FIELD);
        setString(doc, result, OpenApiServer.DESCRIPTION_FIELD);
        setDocument(doc, result, OpenApiServer.VARIABLES_FIELD,
                () -> convertMap(server.variables, this::convertServerVariable));

        return result;
    }

    public IJsonDocument convertServerVariable(OpenApiServerVairable serverVariable) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (serverVariable == null)
            return result;

        IJsonDocument doc = serverVariable.document;

        setDocument(doc, result, OpenApiServerVairable.ENUM_FIELD, () -> convertStringArray(serverVariable.enumValues));
        setString(doc, result, OpenApiServerVairable.DEFAULT_FIELD);
        setString(doc, result, OpenApiServerVairable.DESCRIPTION_FIELD);

        return doc;
    }

    public IJsonDocument convertContact(OpenApiContact contact) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (contact == null)
            return result;

        IJsonDocument doc = contact.document;

        setString(doc, result, OpenApiContact.NAME_FIELD);
        setString(doc, result, OpenApiContact.URL_FIELD);
        setString(doc, result, OpenApiContact.EMAIL_FIELD);

        return result;
    }

    public IJsonDocument convertLicense(OpenApiLicense license) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (license == null)
            return result;

        IJsonDocument doc = license.document;

        setString(doc, result, OpenApiLicense.NAME_FIELD);
        setString(doc, result, OpenApiLicense.IDENTIFIER_FIELD, extensionName(OpenApiLicense.IDENTIFIER_FIELD));
        setString(doc, result, OpenApiLicense.URL_FIELD);

        return result;
    }

    public IJsonDocument convertReference(OpenApiReference<?> parameter) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (parameter == null)
            return result;

        IJsonDocument doc = parameter.document;

        setString(doc, result, OpenApiReference.REF_FIELD);
        //This object cannot be extended with additional properties and any properties added SHALL be ignored.

        return result;
    }

    public <T> IJsonDocument convertMap(Map<String, T> map, ConvertObjectFunction<T> function) {
        IJsonDocument result = IJsonDocument.Factory.makeObject();

        if (map == null || map.entrySet().size() == 0)
            return result;

        for (Map.Entry<String, T> entry : map.entrySet())
            result.setDocument(entry.getKey(), function.convert(entry.getValue()));

        return result;
    }

    public <T> IJsonDocument convertObjectArray(List<T> array, ConvertObjectFunction<T> function) {
        IJsonDocument result = IJsonDocument.Factory.makeArray();

        if (array == null || array.size() == 0)
            return result;

        array.forEach(o -> result.addDocument(function.convert(o)));

        return result;
    }

    public IJsonDocument convertStringArray(List<String> array) {
        IJsonDocument result = IJsonDocument.Factory.makeArray();

        if (array == null || result.size() == 0)
            return result;

        array.forEach(result::addString);

        return result;
    }

    public void setDocument(IJsonDocument from, IJsonDocument to,
                            String fieldName, ConvertFunction function) {
        setDocument(from, to, fieldName, fieldName, function);
    }

    public void setDocument(IJsonDocument from, IJsonDocument to,
                            String fieldNameFrom, String fieldNameTo,
                            ConvertFunction function) {
        if (from.keys().contains(fieldNameFrom))
            to.setDocument(fieldNameTo, function.convert());
    }

/*    public void setExtensions(IJsonDocument doc, Map<String, String> extensions) {
        extensions.forEach((s, s2) ->
                doc.);
    }*/

    private void setString(IJsonDocument from, IJsonDocument to, String fieldName) {
        setString(from, to, fieldName, fieldName);
    }

    private void setString(IJsonDocument from, IJsonDocument to, String fieldNameFrom, String fieldNameTo) {
        if (from.keys().contains(fieldNameFrom))
            to.setString(fieldNameTo, from.getString(fieldNameFrom));
    }

    private String extensionName(String name) {
        return String.format("x-%s", name);
    }

    private interface ConvertFunction {
        IJsonDocument convert();
    }

    private interface ConvertObjectFunction<T> {
        IJsonDocument convert(T obj);
    }
}

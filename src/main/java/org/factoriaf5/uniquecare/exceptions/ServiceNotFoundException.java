package org.factoriaf5.uniquecare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Service not found")
public class ServiceNotFoundException extends RuntimeException {
}

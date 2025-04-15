package com.samples.customers.domain;

import java.util.UUID;

public record CustomerDeletedEvent(
  UUID uuid
) {
}

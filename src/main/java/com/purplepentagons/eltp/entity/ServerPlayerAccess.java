package com.purplepentagons.eltp.entity;

import java.util.UUID;

public interface ServerPlayerAccess {
    int eltp$getKnifeViolenceScore();

    UUID eltp$getKnifeComboEntityUUID();

    void eltp$setKnifeViolenceScore(int newScore);

    void eltp$addKnifeViolenceScore(int newScore);

    void eltp$setKnifeComboEntityUUID(UUID entityUuid);

    void eltp$confirmKnifeHit();
}

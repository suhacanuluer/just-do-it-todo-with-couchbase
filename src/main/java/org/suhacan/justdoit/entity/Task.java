package org.suhacan.justdoit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    private String content;
    private boolean completed;
    private boolean stared;
    private String categoryId;
}

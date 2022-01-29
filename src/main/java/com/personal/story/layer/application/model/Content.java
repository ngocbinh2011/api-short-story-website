package com.personal.story.layer.application.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.*;

@Data
public class Content implements Serializable {

    private String data;
    private static final long serialVersionUID = 3796886095916625573L;

    public Content(String data) {
        this.data = data;
    }

    public static class ContentConverter implements AttributeConverter<Content, byte[]> {
        private static final Logger logger = LoggerFactory.getLogger(Content.class);

        @Override
        public byte[] convertToDatabaseColumn(Content content) {
            if (content != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                    objectOutputStream.writeObject(content);
                    return byteArrayOutputStream.toByteArray();
                } catch (IOException e) {
                    logger.error("cant open object output strean", e);
                } finally {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        logger.error("cant close byte array output stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        public Content convertToEntityAttribute(byte[] bytes) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                return (Content) objectInputStream.readObject();
            } catch (IOException e) {
                logger.error("cant open object input stream", e);
            } catch (ClassNotFoundException e) {
                logger.error("cant cast to content object", e);
            } finally {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "Content{" +
                "data='" + data + '\'' +
                '}';
    }
}

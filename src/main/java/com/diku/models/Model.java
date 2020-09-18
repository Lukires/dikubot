package com.diku.models;

import com.diku.database.Cache;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

import java.util.Objects;

public abstract class Model<T> {


    private static final Cache<Model<Object>, Document> cache = new Cache<Model<Object>, Document>();

    protected final T object;
    protected Document document;
    protected MongoCollection<Document> collection;
    public Model(T object) {
        this.object=object;
        this.collection=getCollection();

        if (!cache.containsKey(this)) {
            document = collection.find(Filters.eq("_id", getID())).first();
            if(document==null) {
                document = init();
                collection.insertOne(document);
            }
            cache.put((Model<Object>) this, document,600);
        }else{
            document = cache.get(this);
        }

    }

    public T getObject() {
        return object;
    }

    protected abstract Document init();
    public abstract MongoCollection<Document> getCollection();
    public abstract String getID();

    public void delete() {
        collection.deleteOne(Filters.eq("_id", getID()));
        init();
    }

    protected void update(String key, Object value) {
        document.append(key, value);
        collection.updateOne(Filters.eq("_id", getID()), new Document("$set", new Document(key, value)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model<?> model = (Model<?>) o;
        return model.getID().equals(((Model<?>) o).getID()) && model.getCollection().equals(((Model<?>) o).getCollection());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + getID().hashCode();
        hash = 31 * hash + getCollection().hashCode();
        hash = 31 * getClass().toString().hashCode();
        return hash;
    }
}

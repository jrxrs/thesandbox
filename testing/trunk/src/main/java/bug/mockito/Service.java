package bug.mockito;

public interface Service<K, V> {

    public V lookup(K key);

}

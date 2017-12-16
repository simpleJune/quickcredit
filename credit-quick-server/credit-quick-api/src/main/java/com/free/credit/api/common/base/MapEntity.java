package com.free.credit.api.common.base;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.free.platform.base.common.BaseEntity;

@SuppressWarnings("serial")
public class MapEntity<K, V> extends BaseEntity implements Map<K, V> {
	
	private Map<K, V> map = new TreeMap<K,V>();
	
	public MapEntity(){
		
	}
	
	public MapEntity(Map<K, V> map){
		this.map = map;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return map.get(key);
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return map.put(key, value);
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		map.putAll(m);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return map.values();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

}

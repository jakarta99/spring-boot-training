package tw.com.softleader.training.policy.repository;

public interface BaseRepository<T, ID> {
    T findWithGraph(ID id, String graphName);
}

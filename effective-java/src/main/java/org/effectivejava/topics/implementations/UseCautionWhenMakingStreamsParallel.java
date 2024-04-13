package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

@AutoService(Item.class)
public class UseCautionWhenMakingStreamsParallel implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_7;
    }

    @Override
    public String getTheme() {
        return "Use caution when making streams parallel";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
            "parallel not always help, look at example with primes",
            "in primes streams library has no idea how to parallelize this pipeline",
            "parallelling a pipeline is unlikely to increase its performance if the source is from Stream.iterate, or " +
                    "the intermediate operation limit is used",
            "do not parallelize the stream pipelines indiscriminately; the performance consequences may be disastrous",
            "performance gains from parallelism are best on streams over ArrayList, HashMap, HashSet, and " +
                    "ConcurrentHashMap instances; arrays; int ranges; and long ranges",
            "those data structures can all be accurately and cheaply split into sub-ranges of any desired sizes",
            "abstraction used by the streams library to perform splitting is the spliterator",
            "the best terminal operations for parallelism are: reductions, such as min, max, count, and sum; also " +
                    "short-circuiting operations anyMatch, allMatch, and noneMatch",
            "not good for parallelism are mutable reductions",
            "for writing own Stream, Iterable, or Collection implementation, for parallelism you must override the " +
                    "spliterator method",
            "not only can paralleling a stream lead to poor performance, including liveliness failures; it can lead to " +
                    "incorrect results and unpredictable behavior",
            "in parallel streams to preserve order in forEach - use forEachOrdered should be used",
            "very rough estimate - the number of elements in the stream times the number of line of code executed per " +
                    "element should be at least a hundred thousand",
            "under the right circumstances, it is possible to achieve near-linear speedup in the number of processor " +
                    "cores simply by adding a parallel call to a stream pipeline",
            "to parallel a stream of random number, start with a SplittableRandom instance, not ThreadLocalRandom or " +
                    "Random"
        );
    }

    @Override
    public void runExamples() {
        primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
//                .parallel()
                .forEach(System.out::println);
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }

    private static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }

    // Prime-counting stream pipeline - benefits from parallelization
    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    // Prime-counting stream pipeline - parallel version
    static long pi_p(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }
}
